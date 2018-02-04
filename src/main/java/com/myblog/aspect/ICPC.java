package com.myblog.aspect;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Zephery
 * @since 2018/1/31 17:15
 */

public class ICPC {

    // define your own attributes, constructor, and methods here
    //To store problems and Teams
    //And respective hashMap to access by findTeam(String) and findProblem(int)
    Vector<Problem> _problemList;
    Vector<Team> _teamList;
    HashMap<Integer, Problem> _problemMap;
    HashMap<String, Team> _teamMap;

    //Constructor to instantiate Vector and HashMap
    ICPC() {
        _problemList = new Vector<Problem>();
        _teamList = new Vector<Team>();
        _problemMap = new HashMap<Integer, Problem>();
        _teamMap = new HashMap<String, Team>();
    }

    public static void main(String[] args) {
        ICPC competition = new ICPC();
        competition.run();
    }

    private void run() {
        //Handling first line of input
        Scanner sc = new Scanner(System.in);
        int numOfTeams = sc.nextInt();
        int numOfProblems = sc.nextInt();
        int numOfQueries = sc.nextInt();
        //further initialise _teamList;
        for (int i = 0; i < numOfTeams; i++) {
            String teamName = sc.next();
            Team newTeam = new Team(teamName, numOfProblems);
            _teamMap.put(teamName, newTeam);
            _teamList.add(newTeam);
        }
        //further initialise _problemList;
        for (int i = 0; i < numOfProblems; i++) {
            Problem newProblem = new Problem(i + 1);
            _problemList.add(newProblem);
            _problemMap.put(i + 1, newProblem);
        }
        //Handling Queries using while
        while (numOfQueries > 0) {
            numOfQueries--;
            switch (sc.next()) {
                //Submit case
                case "SUBMIT": {
                    //Reading input
                    String teamName = sc.next();
                    int problemName = sc.nextInt();
                    int time = sc.nextInt();
                    String verdict = sc.next();
                    //Retrieve team and problem
                    Team team = findTeam(teamName);
                    Problem problem = findProblem(problemName);
                    //Submit call
                    submit(team, problem, time, verdict);
                    break;
                }
                //Details case
                case "DETAILS": {
                    //Reading and retrieving team
                    String teamName = sc.next();
                    Team team = findTeam(teamName);
                    //details call
                    details(team);
                    break;
                }
                //First case
                case "FIRST": {
                    //Reading and retrieving problem
                    int problemName = sc.nextInt();
                    Problem problem = findProblem(problemName);
                    //first call
                    first(problem);
                    break;
                }
                //Unsolved case
                case "UNSOLVED": {
                    //No input, just call
                    unsolved();
                    break;
                }
                //Top case
                case "TOP": {
                    //No input just call
                    top();
                    break;
                }
            }
        }

    }

    //Top method
    private void top() {
        //initialise variables to hold bestTeam
        int maxProbSolved = -1;
        Team bestTeam = null;
        int penalty = -1;
        //Iterate Through _teamList
        for (Team team : _teamList) {
            int currentSolved = team.getNumOfSolved();
            if (currentSolved > maxProbSolved) {
                //if have more problems solved, update all fields
                bestTeam = team;
                maxProbSolved = currentSolved;
                penalty = team.getPenalty();
            }
            if (currentSolved == maxProbSolved) {
                //if have same problem solved, check penalty
                if (team.getPenalty() < penalty) {
                    //if lower penalty, update bestTeam and penalty
                    bestTeam = team;
                    penalty = team.getPenalty();
                }
                if (team.getPenalty() == penalty) {
                    //if same penalty, check lexicographical order of teamName
                    if (team.getName().compareTo(bestTeam.getName()) < 0) {
                        //Lexicographically smaller, update bestTeam
                        bestTeam = team;
                    }
                }
            }
        }
        //call details of bestTeam
        details(bestTeam);
    }

    private void unsolved() {
        //initialise variable to hold for unsolved problem string
        String unsolved = "";
        for (Problem problem : _problemList) {
            //iterate through _problemList
            if (problem.notSolved()) {
                //if unsolved, add to unsolved string
                unsolved = unsolved + problem.getIndex() + " ";
            }
        }
        //Cut the blank at the last char position
        unsolved = unsolved.trim();
        if (unsolved.equals("")) {
            //No unsolved problems added, indicating all solved, print message
            System.out.println("all problems have been solved");
            return;
        }
        //Print unsolved string
        System.out.println(unsolved);
    }

    private void first(Problem problem) {
        //get first team solved from problem ( from its method)
        Team team = problem.getFirst();
        int time = problem.getTime();
        if (team == null) {
            //If not solved, returned null, then print message
            System.out.println("problem " + problem.getIndex() + " has not been solved");
            return;
        }
        //Print first team solved and used time
        System.out.println(team.getName() + " " + time);
    }

    private void details(Team team) {
        //Retrieve variable to be printed from team (from its method)
        String name = team.getName();
        int numOfSolved = team.getNumOfSolved();
        int penalty = team.getPenalty();
        //Print
        System.out.println(name + " " + numOfSolved + " " + penalty);
    }

    private void submit(Team team, Problem problem, int time, String verdict) {
        //Check if it is solved. if so print message and ignore
        if (problem.isSolvedBy(team)) {
            System.out.println("problem already solved");
            return;
        }
        //Get idea which problem is solved
        int problemIndex = problem.getIndex();
        //Get previous submissions to print before being updated
        int previousSubmissions = team.getPreviousSubmissions(problemIndex);
        if (verdict.equals("AC")) {
            //Solved, then update problem and team involved, and then print
            problem.add(team, time);
            team.solve(problem, time);
            System.out.println(team.getName() + " " + verdict + " " + problemIndex + " " + previousSubmissions);
            return;
        } else {
            //Not solved, then update team involved, and then print
            team.attempt(problem);
            System.out.println(team.getName() + " " + verdict + " " + problemIndex + " " + previousSubmissions);
        }
    }

    public Team findTeam(String teamName) {
        //Use hashmap
        return _teamMap.get(teamName);
    }

    public Problem findProblem(int problemName) {
        //Another use of Hashmap
        return _problemMap.get(problemName);
    }
}

class Problem {
    // define your own attributes, constructor, and methods here
    //Problem index, solvedList and time for each team solved
    int _index = 0;
    Vector<Team> _solvedList;
    Vector<Integer> _time;

    Problem(int index) {
        _index = index;
        _solvedList = new Vector<Team>();
        _time = new Vector<Integer>();
    }

    //get first Team solved
    public Team getFirst() {
        if (_solvedList.size() == 0) {
            //if not solved, return null as indication
            return null;
        }
        return _solvedList.get(0);
    }

    //get team used by first team
    public int getTime() {
        if (_time.size() == 0) {
            //if not solved, return -1 as indication
            return -1;
        }
        return _time.get(0);
    }

    //Check whether a team has solved problem, used in submit method
    public boolean isSolvedBy(Team team) {
        //Iterate through solvedList of teams, and check whether team of interest is solved
        for (Team t : _solvedList) {
            if (t == team) {
                //Solved return true
                return true;
            }
        }
        //Not solved, return false
        return false;
    }

    //Get problem index
    public int getIndex() {
        return _index;
    }

    //Add a team which solves the problem in order of submission time
    public void add(Team team, int time) {
        _solvedList.add(team);
        _time.add(time);
    }

    //Check whether a problem is not solved
    public boolean notSolved() {
        if (_solvedList.size() == 0) {
            //Not solved as empty solvedList
            return true;
        }
        //else solved
        return false;
    }
}

class Team {
    // define your own attributes, constructor, and methods here
    String _name;
    int[] _numOfAttempts;
    int _penalty;

    //int _numOfSolved;//Redundant
    Team(String name, int numOfQuestions) {
        _name = name;
        _numOfAttempts = new int[numOfQuestions];
        //This is to initialise array in case not
        for (int i = 0; i < numOfQuestions; i++) {
            _numOfAttempts[i] = 0;
        }
        _penalty = 0;
        //_numOfSolved = 0;
    }

    //get penalty
    public int getPenalty() {
        return _penalty;
    }

    //get previous number of submission
    public int getPreviousSubmissions(int index) {
        return _numOfAttempts[(index - 1)];
    }

    //get team Name
    public String getName() {
        return _name;
    }

    //in case of not solving it, update using this method
    public void attempt(Problem problem) {
        int index = problem.getIndex();
        _numOfAttempts[index - 1]++;
    }

    //in case of solving it, update using this method
    public void solve(Problem problem, int time) {
        int index = problem.getIndex();
        _penalty += _numOfAttempts[index - 1] * 20;
        _numOfAttempts[index - 1] = -1;
        _penalty += time;
        //_numOfSolved++;
    }

    //get idea how many problems are solved
    public int getNumOfSolved() {
        int count = 0;
        for (int i : _numOfAttempts) {
            if (i == -1) {
                count++;
            }
        }
        return count;
    }

}
