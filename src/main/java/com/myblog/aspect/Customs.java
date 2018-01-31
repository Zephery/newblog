package com.myblog.aspect;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Zephery
 * @since 2018/1/31 14:20
 */
public class Customs {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> stacktwo = new Stack<>();
    private Integer count = 0;

    public static void main(String[] args) {
        Customs newCustoms = new Customs();
        newCustoms.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        Integer N = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            String operation = line.split(" ")[0];
            Integer number = Integer.parseInt(line.split(" ")[1]);
            print(operation, number);
        }
    }

    private void print(String operation, Integer number) {
        if ("join".equals(operation)) {
            if (!stacktwo.isEmpty() && number > stacktwo.peek() || stack.size() == 0) {
                stacktwo.push(number);
            }
            stack.push(number);
            System.out.println(stacktwo.size());
        } else {
            for (int i = 0; i < number; i++) {
                Integer poll = stack.pop();
                if (poll.equals(stacktwo.peek()) && !stacktwo.peek().equals(stack.peek()) || stacktwo.size() != 1) {
                    stacktwo.pop();
                }
            }
            System.out.println(stacktwo.size());
        }
    }
}