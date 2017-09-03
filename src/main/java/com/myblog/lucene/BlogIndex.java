package com.myblog.lucene;

import com.myblog.model.Blog;
import com.myblog.util.DateUtil;
import com.myblog.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/7/25 15:23
 * Description: lucene默认使用log4j库，运行中会提示
 */
public class BlogIndex {
    private final static Logger logger = LoggerFactory.getLogger(BlogIndex.class);
    private Directory dir;

    /**
     * refresh lucene
     *
     * @param blogs
     */
    public static void refreshlucene(List<Blog> blogs) {
        try {
            BlogIndex blogIndex = new BlogIndex();
            FileUtils.deleteDirectory(new File("blog_index"));
            for (Blog blog : blogs) {
                blogIndex.addIndex(blog);
            }
        } catch (Exception e) {
            logger.error("refreshlucene error" + e);
        }
    }

    private IndexWriter getWriter() throws Exception {
        dir = FSDirectory.open(Paths.get("blog_index"));
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    public void addIndex(Blog blog) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("blogid", String.valueOf(blog.getBlogid()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        doc.add(new StringField("create_at", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", Jsoup.parse(blog.getContent()).text(), Field.Store.YES));
        doc.add(new StringField("categoryid", blog.getCategory().getcId().toString(), Field.Store.YES));
        doc.add(new TextField("imageurl", blog.getImageurl(), Field.Store.YES));
        doc.add(new StringField("hits", String.valueOf(blog.getHits()), Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    public void deleteIndex(String blogId) throws Exception {
        IndexWriter writer = getWriter();
        writer.deleteDocuments(new Term("blogid", blogId));
        writer.forceMergeDeletes();
        writer.commit();
        writer.close();
    }

    public void updateIndex(Blog blog) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("blogid", String.valueOf(blog.getBlogid()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        doc.add(new TextField("content", Jsoup.parse(blog.getContent()).text(), Field.Store.YES));
        writer.updateDocument(new Term("blogid", String.valueOf(blog.getBlogid())), doc);
        writer.close();
    }

    /**
     * 首先是对标题进行查找，然后对文章内容进行查找
     *
     * @param pageStart
     * @param q
     * @param pagehits
     * @return
     * @throws Exception
     */
    public List<Blog> searchBlog(Integer pageStart, String q, Integer pagehits) throws Exception {
        dir = FSDirectory.open(Paths.get("blog_index"));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher search = new IndexSearcher(reader);
        ScoreDoc lastBottom = null;//相当于pageSize
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        QueryParser parser1 = new QueryParser("title", analyzer);//对文章标题进行搜索
        Query query1 = parser1.parse(q);
        QueryParser parser2 = new QueryParser("content", analyzer);//对文章内容进行搜索
        Query query2 = parser2.parse(q);
        booleanQuery.add(query1, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
        TopDocs hits = search.searchAfter(lastBottom, booleanQuery.build(), pagehits);  //lastBottom（pageSize），pagehits（pagenum）
        QueryScorer scorer = new QueryScorer(query1);         //
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        List<Blog> blogIndexList = new LinkedList<>();
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = search.doc(scoreDoc.doc);
            Blog blog = new Blog();
            blog.setBlogid(Integer.parseInt(doc.get(("blogid"))));
            blog.setCreateAt(DateTime.parse(doc.get("create_at")).toString());
            blog.setImageurl(doc.get("imageurl"));
            blog.setCategoryid(Integer.parseInt(doc.get("categoryid")));
            blog.setHits(Integer.parseInt(doc.get("hits")));
            String title = doc.get("title");
            String content = doc.get("content");
            if (title != null) {
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                if (StringUtil.isEmpty(hTitle)) {
                    blog.setTitle(title);
                } else {
                    blog.setTitle(hTitle);
                }
            }
            if (content != null) {
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String hContent = highlighter.getBestFragment(tokenStream, content);
                if (StringUtil.isEmpty(hContent)) {
                    if (content.length() <= 400) {//对结果进行截取
                        blog.setSummary(content);
                    } else {
                        blog.setSummary(content.substring(0, 400));
                    }
                } else {
                    blog.setSummary(hContent);
                }
            }
            blogIndexList.add(blog);
        }
        return blogIndexList;
    }

    public static void main(String args[]) {
        try {
            List<Blog> list = new BlogIndex().searchBlog(1, "j", 4);
            for (Blog blog : list) {
                System.out.println(blog.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
