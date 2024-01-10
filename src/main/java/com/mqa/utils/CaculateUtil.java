package com.mqa.utils;

import com.mqa.entity.StoneImage;
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

    //评价矩阵
    private static Double[][] matrix = {
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
    };

    private static final Double a = 0.33;
    private static final Double b = 0.66;
    private static final Double c = 1.0;

    private static ArrayList<Double> evaluationResult;
    private static final Double[] resultPoint = {0.4, 0.3, 0.3};

    /**
     * 计算隶属度函数
     *
     * @param x
     * @return
     */
    private static Double getMembership1(Double x) {
        if (x <= a) {
            return 1.0;
        } else if (a < x && x <= b) {
            return (b - x) / (b - a);
        } else {
            return 0.0;
        }
    }

    private static Double getMembership2(Double x) {
        if (a <= x && x <= b) {
            return (x - a) / (b - a);
        } else if (b < x && x <= c) {
            return (c - x) / (c - b);
        } else {
            return 0.0;
        }
    }

    private static Double getMembership3(Double x) {
        if (x <= b) {
            return 0.0;
        } else if (b < x && x <= c) {
            return (x - b) / (c - b);
        } else {
            return 1.0;
        }
    }

    //获取每张图片的隶属度矩阵
    private static void updateMartrix(StoneImage stoneImage) {
        Double stainProportion = stoneImage.getStainProportion();
        Double stainColorDiffer = stoneImage.getStainColorDiffer();
        Integer crackNum = stoneImage.getCrackNum();
        Double lengthCaculated = stoneImage.getLengthCaculated();
        Double widthCaculated = stoneImage.getWidthCaculated();
        Double crackAreaPercent = stoneImage.getCrackAreaPercent();

        matrix[0][0] = getMembership1((stainProportion - stainProportionMin) / (stainProportionMax - stainProportionMin));
        matrix[0][1] = getMembership2((stainProportion - stainProportionMin) / (stainProportionMax - stainProportionMin));
        matrix[0][2] = getMembership3((stainProportion - stainProportionMin) / (stainProportionMax - stainProportionMin));
        matrix[1][0] = getMembership1((stainColorDiffer - stainColorDifferMin) / (stainColorDifferMax - stainColorDifferMin));
        matrix[1][1] = getMembership2((stainColorDiffer - stainColorDifferMin) / (stainColorDifferMax - stainColorDifferMin));
        matrix[1][2] = getMembership3((stainColorDiffer - stainColorDifferMin) / (stainColorDifferMax - stainColorDifferMin));
        matrix[2][0] = getMembership1((crackNum - crackNumMin) / (crackNumMax - crackNumMin));
        matrix[2][1] = getMembership2((crackNum - crackNumMin) / (crackNumMax - crackNumMin));
        matrix[2][2] = getMembership3((crackNum - crackNumMin) / (crackNumMax - crackNumMin));
        matrix[3][0] = getMembership1((lengthCaculated - lengthCaculatedMin) / (lengthCaculatedMax - lengthCaculatedMin));
        matrix[3][1] = getMembership2((lengthCaculated - lengthCaculatedMin) / (lengthCaculatedMax - lengthCaculatedMin));
        matrix[3][2] = getMembership3((lengthCaculated - lengthCaculatedMin) / (lengthCaculatedMax - lengthCaculatedMin));
        matrix[4][0] = getMembership1((widthCaculated - widthCaculatedMin) / (widthCaculatedMax - widthCaculatedMin));
        matrix[4][1] = getMembership2((widthCaculated - widthCaculatedMin) / (widthCaculatedMax - widthCaculatedMin));
        matrix[4][2] = getMembership3((widthCaculated - widthCaculatedMin) / (widthCaculatedMax - widthCaculatedMin));
        matrix[5][0] = getMembership1((crackAreaPercent - crackAreaPercentMin) / (crackAreaPercentMax - crackAreaPercentMin));
        matrix[5][1] = getMembership2((crackAreaPercent - crackAreaPercentMin) / (crackAreaPercentMax - crackAreaPercentMin));
        matrix[5][2] = getMembership3((crackAreaPercent - crackAreaPercentMin) / (crackAreaPercentMax - crackAreaPercentMin));

    }

    private static Double getPoint() {
        evaluationResult = new ArrayList<>();
        double result;
        for (int i = 0; i < 3; i++) {
            result = 0.0;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 6; k++) {
                    result += weight.get(k) * matrix[k][j];
                }
            }
            evaluationResult.add(result);
        }
        return evaluationResult.get(0) * resultPoint[0] + evaluationResult.get(1) * resultPoint[1] + evaluationResult.get(2) * resultPoint[2];
    }

    /**
     * 使用熵值法计算权重
     */
    public static void caculateWeight() {

        int dataSize = stainProportion.size();
        //首先清空原来的权重值
        weight.clear();
        double[] paramMax = new double[]{0, 0, 0, 0, 0, 0};
        double[] paramMin = new double[]{1e12, 1e12, 1e12, 1e12, 1e12, 1e12};
        double[][] y = new double[dataSize][6];
        //第一个参数：污渍面积占比
        for (double param : stainProportion) {
            if (param > paramMax[0]) {
                paramMax[0] = param;
            } else if (param < paramMin[0]) {
                paramMin[0] = param;
            }
        }
        for (int i = 0; i < dataSize; i++) {
            y[i][0] = (paramMax[0] - stainProportion.get(i)) / (paramMax[0] - paramMin[0]);
        }
        stainProportionMax = paramMax[0];
        stainProportionMin = paramMin[0];
        //第二个参数：色差
        for (double param : stainColorDiffer) {
            if (param > paramMax[1]) {
                paramMax[1] = param;
            } else if (param < paramMin[1]) {
                paramMin[1] = param;
            }
        }
        for (int i = 0; i < dataSize; i++) {
            y[i][1] = (paramMax[1] - stainColorDiffer.get(i)) / (paramMax[1] - paramMin[1]);
        }
        stainColorDifferMax = paramMax[1];
        stainColorDifferMin = paramMin[1];
        //第三个参数：裂缝条数
        for (double param : crackNum) {
            if ((double) param > paramMax[2]) {
                paramMax[2] = (double) param;
            } else if ((double) param < paramMin[2]) {
                paramMin[2] = (double) param;
            }
        }
        for (int i = 0; i < dataSize; i++) {
            y[i][2] = (paramMax[2] - crackNum.get(i)) / (paramMax[2] - paramMin[2]);
        }
        crackNumMax = paramMax[2];
        crackNumMin = paramMin[2];
        //第四个参数：裂缝长度平方与面积的比值
        for (double param : lengthCaculated) {
            if (param > paramMax[3]) {
                paramMax[3] = param;
            } else if (param < paramMin[3]) {
                paramMin[3] = param;
            }
        }
        for (int i = 0; i < dataSize; i++) {
            y[i][3] = (paramMax[3] - lengthCaculated.get(i)) / (paramMax[3] - paramMin[3]);
        }
        lengthCaculatedMax = paramMax[3];
        lengthCaculatedMin = paramMin[3];
        //第五个参数：裂缝最大宽度平方与面积的比值
        for (double param : widthCaculated) {
            if (param > paramMax[4]) {
                paramMax[4] = param;
            } else if (param < paramMin[4]) {
                paramMin[4] = param;
            }
        }
        for (int i = 0; i < dataSize; i++) {
            y[i][4] = (paramMax[4] - widthCaculated.get(i)) / (paramMax[4] - paramMin[4]);
        }
        widthCaculatedMax = paramMax[4];
        widthCaculatedMin = paramMin[4];
        //第六个参数：裂缝面积占比
        for (double param : crackAreaPercent) {
            if (param > paramMax[5]) {
                paramMax[5] = param;
            } else if (param < paramMin[5]) {
                paramMin[5] = param;
            }
        }
        for (int i = 0; i < dataSize; i++) {
            y[i][5] = (paramMax[5] - crackAreaPercent.get(i)) / (paramMax[5] - paramMin[5]);
        }

        crackAreaPercentMax = paramMax[5];
        crackAreaPercentMin = paramMin[5];

        double[][] p = new double[dataSize][6];
        for (int j = 0; j < 6; j++) {
            double sumNum = 0;
            for (int i = 0; i < dataSize; i++) {
                sumNum += y[i][j];
            }
            for (int i = 0; i < dataSize; i++) {
                p[i][j] = y[i][j] / sumNum;
            }
        }

        //计算第j个参数Ej的信息熵值
        double[] E=new double[6];
        for(int j=0;j<6;j++){
            double sum=0;
            for(int i=0;i<dataSize;i++){
                sum+=p[i][j]*Math.log(p[i][j]+0.000001);
            }
            E[j] = -sum / Math.log(dataSize);
        }

        //根据信息熵的值计算权重
        double sumE=0;
        for(int j=0;j<6;j++){
            sumE+=E[j];
        }
        for(int j=0;j<6;j++){
            weight.add((1-E[j])/(6-sumE));

        }
    }

    /**
     * 计算韧性得分
     */
    public static Double caculatePoint(StoneImage stoneImage) {
        //获取石材图片的评价矩阵
        updateMartrix(stoneImage);
        //计算韧性得分
        return getPoint();
    }


}
