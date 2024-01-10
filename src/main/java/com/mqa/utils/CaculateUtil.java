package com.mqa.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class CaculateUtil {
    private static ArrayList<Double> weight;

    public static ArrayList<Double> stainProportion;
    public static ArrayList<Double> stainColorDiffer;
    public static ArrayList<Integer> crackNum;
    public static ArrayList<Double> lengthCaculated;
    public static ArrayList<Double> widthCaculated;
    public static ArrayList<Double> crackAreaPercent;
    private static double stainProportionMax;
    private static double stainProportionMin;
    private static double stainColorDifferMax;
    private static double stainColorDifferMin;
    private static double crackNumMax;
    private static double crackNumMin;
    private static double lengthCaculatedMax;
    private static double lengthCaculatedMin;
    private static double widthCaculatedMax;
    private static double widthCaculatedMin;
    private static double crackAreaPercentMax;
    private static double crackAreaPercentMin;

    /**
     * 计算权重，算法：熵值法
     */
    public static void caculateWeight() {
        int dataSize=stainProportion.size();
        weight.clear();
        double[] paramMax=new double[]{0,0,0,0,0,0};
        double[] paramMin=new double[]{1e12,1e12,1e12,1e12,1e12,1e12};
        double[][] y=new double[dataSize][6];
        //第一个参数：污渍面积占比
        for(double param : stainProportion){
            if(param>paramMax[0]){
                paramMax[0]=param;
            }
            else if(param<paramMin[0]){
                paramMin[0]=param;
            }
        }
        for(int i=0;i<dataSize;i++){
            y[i][0]=(paramMax[0]-stainProportion.get(i))/(paramMax[0]-paramMin[0]);
        }
        stainProportionMax=paramMax[0];
        stainProportionMin=paramMin[0];
        //第二个参数：色差
        for(double param : stainColorDiffer){
            if(param>paramMax[1]){
                paramMax[1]=param;
            }
            else if(param<paramMin[1]){
                paramMin[1]=param;
            }
        }
        for(int i=0;i<dataSize;i++){
            y[i][1]=(paramMax[1]-stainColorDiffer.get(i))/(paramMax[1]-paramMin[1]);
        }
        stainColorDifferMax=paramMax[1];
        stainColorDifferMin=paramMin[1];
        //第三个参数：裂缝条数
        for(double param : crackNum){
            if((double)param>paramMax[2]){
                paramMax[2]=(double)param;
            }
            else if((double)param<paramMin[2]){
                paramMin[2]=(double)param;
            }
        }
        for(int i=0;i<dataSize;i++){
            y[i][2]=(paramMax[2]-crackNum.get(i))/(paramMax[2]-paramMin[2]);
        }
        crackNumMax=paramMax[2];
        crackNumMin=paramMin[2];
        //第四个参数：裂缝长度平方与面积的比值
        for(double param : lengthCaculated){
            if(param>paramMax[3]){
                paramMax[3]=param;
            }
            else if(param<paramMin[3]){
                paramMin[3]=param;
            }
        }
        for(int i=0;i<dataSize;i++){
            y[i][3]=(paramMax[3]-lengthCaculated.get(i))/(paramMax[3]-paramMin[3]);
        }
        lengthCaculatedMax=paramMax[3];
        lengthCaculatedMin=paramMin[3];
        //第五个参数：裂缝最大宽度平方与面积的比值
        for(double param : widthCaculated){
            if(param>paramMax[4]){
                paramMax[4]=param;
            }
            else if(param<paramMin[4]){
                paramMin[4]=param;
            }
        }
        for(int i=0;i<dataSize;i++){
            y[i][4]=(paramMax[4]-widthCaculated.get(i))/(paramMax[4]-paramMin[4]);
        }
        widthCaculatedMax=paramMax[4];
        widthCaculatedMin=paramMin[4];
        //第六个参数：裂缝面积占比
        for(double param : crackAreaPercent){
            if(param>paramMax[5]){
                paramMax[5]=param;
            }
            else if(param<paramMin[5]){
                paramMin[5]=param;
            }
        }
        for(int i=0;i<dataSize;i++){
            y[i][5]=(paramMax[5]-crackAreaPercent.get(i))/(paramMax[5]-paramMin[5]);
        }
        crackAreaPercentMax=paramMax[5];
        crackAreaPercentMin=paramMin[5];

        double[][] p=new double[dataSize][6];
        for(int j=0;j<6;j++){
            double sumNum=0;
            for(int i=0;i<dataSize;i++){
                sumNum+=y[i][j];
            }
            for(int i=0;i<dataSize;i++){
                p[i][j]=y[i][j]/sumNum;
            }
        }
        double[] E=new double[6];
        for(int j=0;j<6;j++){
            double sum=0;
            for(int i=0;i<dataSize;i++){
                sum+=p[i][j]*Math.log(p[i][j]+0.000001);
            }
            E[j]=-sum/Math.log(dataSize);
        }
        double sumE=0;
        for(int j=0;j<6;j++){
            sumE+=E[j];
        }
        for(int j=0;j<6;j++){
            weight.add((1-E[j])/(dataSize-sumE));
        }
    }

    /**
     * 计算韧性得分
     */
    public static Double caculatePoint() {
        return 0.0;
    }


}
