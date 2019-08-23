package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/6/4 17:58
 * 解释器模式
 * 主要角色：
 */
public class InterpretTest {
    /**
     * 抽象表达式
     * */
    interface AbstractExpression{
        Object interpret(String info);
    }

    /**
     * 非终结符表达式角色
     * */
    class NonterminalExpression implements AbstractExpression{
        private AbstractExpression exp1;
        private AbstractExpression exp2;
        @Override
        public Object interpret(String info) {
            return null;
        }
    }

    /**
     * 环境类
     * */
    class Context{
        private AbstractExpression exp;

        public Context() {

        }

        public void operation(String info){
            exp.interpret(info);
        }
    }
    
    /**
     * jep java expression parser
     * java表达式分析器
     * 
     * */
    
    @Test
    public void jepTest(){
    }
}
