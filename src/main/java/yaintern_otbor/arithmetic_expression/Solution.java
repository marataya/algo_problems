package coderun.arithmetic_expression;

/*
* В далёкой галактике, на планете Разделяндия, жители одержимы математическими выражениями. Однако их выражения для нас кажутся слишком простыми, и всегда стоит вопрос: «А минусы будут?». Вам дана строка S SS, представляющая собой математическое выражение, содержащее целые числа, операции сложения «+» и вычитания «-», а также скобки «(» и «)» для определения порядка операций. Сами числа, операторы и скобки могут быть разделены пробелами. Ваша задача — ответить жителям Разделяндии, вычислив итоговое значение их выражения. Гарантируется, что:
* Строка содержит только допустимые символы: цифры, пробелы, операторы «+» и «-», а также круглые скобки «(» и «)».
* Строка корректно сбалансирована по скобкам (каждая открывающая скобка имеет соответствующую закрывающую).
* Пустых скобок нет (вида «()»).
* Все числа во входной строке – целые числа, и их значения входят в 32-битный целочисленный диапазон.
* Результат вычисления выражения входит в 64-битный целочисленный диапазон.

Формат ввода
Единственная строка S(1 ≤ ∣S∣ ≤ 10^6) содержит в себе математическое выражение, которое необходимо вычислить.

Формат вывода
Одно целое число – результат вычисления выражения.

* Пример 1

Ввод
-123 + 23

Вывод
-100

* Пример 2

Ввод
-((5 -2) - (3) +2) + 1

Вывод
-1

* Пример 3

Ввод
(1 + -2) + (3-4 - (5-6 - 7)) +8

Вывод
14

* Пример 4

Ввод
--42

Вывод
42

* Примечания
Обратите внимание, что ваше решение должно корректно обрабатывать выражения с неявно указанными операциями. Например, если перед скобками стоит знак «-», он должен применяться ко всему подвыражению внутри скобок. Также необходимо учесть корректный приоритет операций и правильную работу с отрицательными числами.
* */

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String expression = reader.readLine();
        long result = evaluate(expression);

        writer.write(String.valueOf(result));

        reader.close();
        writer.close();
    }

    private static long evaluate(String expression) {
        // Remove all spaces
        expression = expression.replaceAll("\\s+", "");

        return evaluateExpression(expression, 0)[0];
    }

    // Returns [value, position after the evaluated expression]
    private static long[] evaluateExpression(String expression, int position) {
        Deque<Long> values = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();

        boolean expectOperand = true; // Expecting a number or opening parenthesis

        while (position < expression.length()) {
            char c = expression.charAt(position);

            if (c == '(') {
                // Evaluate the sub-expression
                long[] result = evaluateExpression(expression, position + 1);
                values.push(result[0]);
                position = (int) result[1];
                expectOperand = false;
                continue;
            } else if (c == ')') {
                // End of current sub-expression
                break;
            } else if (c == '+' || c == '-') {
                if (expectOperand) {
                    // Unary operator (like in -5 or +3)
                    if (c == '-') {
                        // Count consecutive minus signs
                        int minusCount = 1;
                        while (position + 1 < expression.length() && expression.charAt(position + 1) == '-') {
                            minusCount++;
                            position++;
                        }

                        // Determine if the final sign is negative (odd number of minuses) or positive (even number)
                        boolean isNegative = (minusCount % 2 == 1);

                        position++;

                        if (position < expression.length() && expression.charAt(position) == '(') {
                            // Handle sub-expression after unary operator
                            long[] result = evaluateExpression(expression, position + 1);
                            values.push(isNegative ? -result[0] : result[0]);
                            position = (int) result[1];
                        } else {
                            // Handle number after unary operator
                            long[] result = getNumber(expression, position);
                            values.push(isNegative ? -result[0] : result[0]);
                            position = (int) result[1];
                        }
                        expectOperand = false;
                        continue;
                    } else {
                        // Skip unary plus
                        position++;
                        continue;
                    }
                }

                // Process any pending operations with equal or higher precedence
                while (!ops.isEmpty()) {
                    applyOp(values, ops);
                }

                ops.push(c);
                expectOperand = true;
            } else if (Character.isDigit(c)) {
                // Parse the number
                long[] result = getNumber(expression, position);
                values.push(result[0]);
                position = (int) result[1];
                expectOperand = false;
                continue;
            }

            position++;
        }

        // Process any remaining operations
        while (!ops.isEmpty()) {
            applyOp(values, ops);
        }

        return new long[] { values.peek(), position + 1 };
    }

    private static long[] getNumber(String expression, int position) {
        long num = 0;
        while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
            num = num * 10 + (expression.charAt(position) - '0');
            position++;
        }
        return new long[] { num, position };
    }

    private static void applyOp(Deque<Long> values, Deque<Character> ops) {
        char op = ops.pop();
        long b = values.pop();
        long a = values.pop();

        switch (op) {
            case '+':
                values.push(a + b);
                break;
            case '-':
                values.push(a - b);
                break;
        }
    }
}

