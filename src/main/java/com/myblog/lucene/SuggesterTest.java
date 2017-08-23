package com.myblog.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/7/26 11:51
 * Description:
 */
public class SuggesterTest {
    private static void lookup(AnalyzingInfixSuggester suggester, String keyword,
                               String region) throws IOException {
        HashSet<BytesRef> contexts = new HashSet<BytesRef>();
        contexts.add(new BytesRef(region.getBytes("UTF8")));
        //先以contexts为过滤条件进行过滤，再以keyword为关键字进行筛选，根据weight值排序返回前2条
        //第3个布尔值即是否每个Term都要匹配，第4个参数表示是否需要关键字高亮
        List<Lookup.LookupResult> results = suggester.lookup(keyword, 2, true, false);
        System.out.println("-- \"" + keyword + "\" (" + region + "):");
        for (Lookup.LookupResult result : results) {
            System.out.println(result.key);
            //从payload中反序列化出Product对象
            BytesRef bytesRef = result.payload;
            InputStream is = Tools.bytes2InputStream(bytesRef.bytes);
            Product product = (Product) Tools.deSerialize(is);
            System.out.println("product-Name:" + product.getName());
            System.out.println("product-regions:" + product.getRegions());
            System.out.println("product-image:" + product.getImage());
            System.out.println("product-numberSold:" + product.getNumberSold());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            RAMDirectory indexDir = new RAMDirectory();
            StandardAnalyzer analyzer = new StandardAnalyzer();
            AnalyzingInfixSuggester suggester = new AnalyzingInfixSuggester(indexDir, analyzer);

            //创建Product测试数据
            ArrayList<Product> products = new ArrayList<Product>();
            products.add(new Product("Electric Guitar",
                    "http://images.example/electric-guitar.jpg", new String[]{
                    "US", "CA"}, 100));
            products.add(new Product("Electric Train",
                    "http://images.example/train.jpg", new String[]{"US",
                    "CA"}, 100));
            products.add(new Product("Acoustic Guitar",
                    "http://images.example/acoustic-guitar.jpg", new String[]{
                    "US", "ZA"}, 80));
            products.add(new Product("Guarana Soda",
                    "http://images.example/soda.jpg",
                    new String[]{"ZA", "IE"}, 130));

            // 创建测试索引
            suggester.build(new ProductIterator(products.iterator()));

            // 开始搜索
            lookup(suggester, "Gu", "US");
//            lookup(suggester, "Gu", "ZA");
//            lookup(suggester, "Gui", "CA");
//            lookup(suggester, "Electric guit", "US");
        } catch (IOException e) {
            System.err.println("Error!");
        }
    }
}