# Neuroph XOR 神经网络实现

## 简介
本文档展示如何使用 Neuroph 框架实现一个解决 XOR（异或）问题的神经网络。XOR 是一个经典的非线性分类问题，需要使用多层神经网络来解决。

## XOR 真值表
| 输入 A | 输入 B | 输出 |
|--------|--------|------|
| 0      | 0      | 0    |
| 0      | 1      | 1    |
| 1      | 0      | 1    |
| 1      | 1      | 0    |

## 代码实现

```java
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
public class NeurophXOR {
public static void main(String[] args) {
// 创建神经网络
NeuralNetwork neuralNetwork = assembleNeuralNetwork();
// 训练神经网络
trainNeuralNetwork(neuralNetwork);
// 测试神经网络
testNeuralNetwork(neuralNetwork);
}
private static NeuralNetwork assembleNeuralNetwork() {
// 创建一个多层感知机，使用以下结构：
// 输入层(2个神经元) -> 隐藏层1(4个神经元) -> 隐藏层2(4个神经元) -> 输出层(1个神经元)
return new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 2, 4, 4, 1);
}
private static void trainNeuralNetwork(NeuralNetwork neuralNetwork) {
// 创建训练数据集
DataSet trainingSet = new DataSet(2, 1);
// 添加训练数据
trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));
// 训练神经网络
neuralNetwork.learn(trainingSet);
}
private static void testNeuralNetwork(NeuralNetwork neuralNetwork) {
// 测试所有可能的输入组合
double[][] testInputs = {{0,0}, {0,1}, {1,0}, {1,1}};
for (double[] testInput : testInputs) {
neuralNetwork.setInput(testInput);
neuralNetwork.calculate();
double[] output = neuralNetwork.getOutput();
System.out.printf("测试输入: %d, %d, 输出: %.3f%n",
(int)testInput[0], (int)testInput[1], output[0]);
}
}
}
```


## 代码说明

### 网络结构
- 输入层：2个神经元（对应XOR的两个输入）
- 第一隐藏层：4个神经元
- 第二隐藏层：4个神经元
- 输出层：1个神经元（对应XOR的输出）

### 主要方法
1. `assembleNeuralNetwork()`: 创建并配置神经网络结构
2. `trainNeuralNetwork()`: 准备训练数据并训练网络
3. `testNeuralNetwork()`: 测试训练后的网络性能

### 激活函数
使用 Sigmoid 函数作为神经元的激活函数，这是处理非线性分类问题的常用选择。

## 使用说明
1. 确保已添加 Neuroph 依赖到项目中
2. 运行 main 方法即可看到训练结果
3. 网络训练完成后会自动测试所有可能的输入组合

## 注意事项
- 由于神经网络的随机初始化特性，每次运行可能得到略有不同的结果
- 如果输出结果不够理想，可以：
  - 增加训练轮数
  - 调整网络结构
  - 修改学习率
  - 使用不同的激活函数