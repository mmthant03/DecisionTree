# Decision Tree

This project is extracting features from 1000 connect4 configurations and building decision trees.

Author: Myo Min Thant, Robert Dutile

## Prerequisites

* Java >8 and <12
* WEKA http://www.cs.waikato.ac.nz/ml/weka/index_downloading.html

## How to build and run

You can use your IDE or text editors of your choice.
Simply run the main program which is src/DecisionTree.java.
The program requires two arguments. The first argument is input file in csv type.
The second one is output file name in csv type. 
If you do not give an input file path/name, the program will exit with error message. 
If you do not specified your output file name, the program will create a new output file with default name "output.csv" 
inside trainingData folder.

Next, use the output file generated from the program in WEKA. 

## Notes

We included screenshots of the steps we took while working on WEKA inside report.
Also, for your convenience, we also included our trained models inside WEKA_models folder.


