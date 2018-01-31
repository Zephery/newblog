package com.myblog.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Zephery
 * @since 2018/1/31 17:15
 */
public class ICPC {

    private List<Team> teams = new ArrayList<>();
    private List<Problem> problems = new ArrayList<>();

    public static void main(String[] args) {

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Integer n = Integer.parseInt(line.split(" ")[0]);
        Integer p = Integer.parseInt(line.split(" ")[1]);
        Integer q = Integer.parseInt(line.split(" ")[2]);
        for (int i = 0; i < n; i++) {
            String teamName = scanner.nextLine();
            Team team = new Team();
            team.setName(teamName);
            teams.add(team);
        }
        for (int i = 0; i < q; i++) {
            String query = scanner.nextLine();
            String operation = query.split(" ")[0];
            if ("UNSOLVED".equals(operation)) {

            } else if ("SUBMIT".equals(operation)) {
                String subName = query.split(" ")[1];
                String questionNum = query.split(" ")[2];
                String minutes = query.split(" ")[3];
                String verdict = query.split(" ")[4];
            } else if ("DETAILS".equals(operation)) {
                String detailName=query.split(" ")[1];

            } else if ("FIRST".equals(operation)) {

            } else if ("TOP".equals(operation)) {

            }
        }
    }
}

class Problem {
    private List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}

class Team {
    private String name;
    private String takeTime;
    private String solve;
    private String verdict;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }
}