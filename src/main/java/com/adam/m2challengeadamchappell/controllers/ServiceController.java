package com.adam.m2challengeadamchappell.controllers;

// Project Imports
import com.adam.m2challengeadamchappell.models.MathSolution;
import com.adam.m2challengeadamchappell.models.Month;

// Java Imports
import java.util.ArrayList;
import java.util.List;

// Spring Imports
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class ServiceController {

    private final List<Month> allMonths;

    public ServiceController(){
        allMonths = new ArrayList<>();
        allMonths.add(new Month(1, "January"));
        allMonths.add(new Month(2, "February"));
        allMonths.add(new Month(3, "March"));
        allMonths.add(new Month(4, "April"));
        allMonths.add(new Month(5, "May"));
        allMonths.add(new Month(6, "June"));
        allMonths.add(new Month(7, "July"));
        allMonths.add(new Month(8, "August"));
        allMonths.add(new Month(9, "September"));
        allMonths.add(new Month(10, "October"));
        allMonths.add(new Month(11, "November"));
        allMonths.add(new Month(12, "December"));
    }

    @RequestMapping(value="/month/{monthNumber}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Month getMonthByNumber(@PathVariable int monthNumber) {
        // Range Check for Exceptions
        if(monthNumber >= 1 && monthNumber <= 12){
            // Shift from Month Number to Index
            return allMonths.get(monthNumber - 1);
        } else {
            throw new IllegalArgumentException("You Must Request a Month between 1 and 12");
        }
    }

    @RequestMapping(value="/randomMonth", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Month getRandomMonth(){
        // Get the random Month Index (0 to 11) as an Integer
        int monthToGet = (int) ((Math.random() * 12 ));
        return allMonths.get(monthToGet);
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public MathSolution doAdditionMathSolution(@RequestBody MathSolution mathSolution){
        if(mathSolution.getOperand1() == null || mathSolution.getOperand2() == null){
            throw new IllegalArgumentException("You must include both operand values in your request, and both must be numeric values.");
        }
        mathSolution.setOperation("add");
        mathSolution.setAnswer(mathSolution.getOperand1() + mathSolution.getOperand2());
        return mathSolution;
    }

    @RequestMapping(value="/subtract", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public MathSolution doSubtractionMathSolution(@RequestBody MathSolution mathSolution){
        if(mathSolution.getOperand1() == null || mathSolution.getOperand2() == null){
            throw new IllegalArgumentException("You must include both operand values in your request, and both must be numeric values.");
        }
        mathSolution.setOperation("subtract");
        mathSolution.setAnswer(mathSolution.getOperand1() - mathSolution.getOperand2());
        return mathSolution;
    }

    @RequestMapping(value="/multiply", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public MathSolution doMultiplicationMathSolution(@RequestBody MathSolution mathSolution){
        if(mathSolution.getOperand1() == null || mathSolution.getOperand2() == null){
            throw new IllegalArgumentException("You must include both operand values in your request, and both must be numeric values.");
        }
        mathSolution.setOperation("multiply");
        mathSolution.setAnswer(mathSolution.getOperand1() * mathSolution.getOperand2());
        return mathSolution;
    }

    @RequestMapping(value="/divide", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public MathSolution doDivisionMathSolution(@RequestBody MathSolution mathSolution){
        if(mathSolution.getOperand1() == null || mathSolution.getOperand2() == null){
            throw new IllegalArgumentException("You must include both operand values in your request, and both must be numeric values.");
        }
        if(mathSolution.getOperand2() == 0){
            throw new IllegalArgumentException("Operand 2 May not be zero for division.");
        }
        mathSolution.setOperation("divide");
        mathSolution.setAnswer(mathSolution.getOperand1() / mathSolution.getOperand2());
        return mathSolution;
    }

}