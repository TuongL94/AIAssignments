%% Batch gradient descent

dataset1 = scaledEnglishData;
dataset2 = scaledFrenchData;

dataset3 = englishData;
dataset4 = frenchData;

[scaled_w1, nbr1] = batchGradientDescent(dataset1);
[scaled_w2, nbr2] = batchGradientDescent(dataset2);

w1(1) = max(dataset3(:,2))*scaled_w1(1);
w1(2) = max(dataset3(:,2))/max(dataset3(:,1))*scaled_w1(2);

w2(1) = max(dataset4(:,2))*scaled_w2(1);
w2(2) = max(dataset4(:,2))/max(dataset4(:,1))*scaled_w2(2);

grid = linspace(10000,80000,1000);

figure(1)
plot(dataset3(:,1),dataset3(:,2),'ro')
hold on
plot(grid, w1(1)+w1(2)*grid, 'r')
plot(dataset4(:,1),dataset4(:,2),'x')
plot(grid, w2(1)+w2(2)*grid, 'b')
%% Stochastic gradient descent

dataset1 = scaledEnglishData;
dataset2 = scaledFrenchData;

dataset3 = englishData;
dataset4 = frenchData;

[scaled_w1, nbr1] = stochasticGradientDescent(dataset1);
[scaled_w2, nbr2] = stochasticGradientDescent(dataset2);

w1(1) = max(dataset3(:,2))*scaled_w1(1);
w1(2) = max(dataset3(:,2))/max(dataset3(:,1))*scaled_w1(2);

w2(1) = max(dataset4(:,2))*scaled_w2(1);
w2(2) = max(dataset4(:,2))/max(dataset4(:,1))*scaled_w2(2);

grid = linspace(10000,80000,1000);

figure(1)
plot(dataset3(:,1),dataset3(:,2),'ro')
hold on
plot(grid, w1(1)+w1(2)*grid, 'r')
plot(dataset4(:,1),dataset4(:,2),'x')
plot(grid, w2(1)+w2(2)*grid, 'b')


%% Perceptron

eng = englishData;
fr = frenchData;
dataset = [eng; fr];
data = zeros(size(dataset,1), size(dataset,2));
data(:,1) = dataset(:,1)/max(dataset(:,1));
data(:,2) = dataset(:,2)/max(dataset(:,2));

label = [ones(length(eng),1); zeros(length(fr),1)];

w_scaled = myPerceptron(label,data);
m_scaled = -w_scaled(1)/w_scaled(3);
k_scaled = -w_scaled(2)/w_scaled(3);

m = max(dataset(:,2))*m_scaled;
k = max(dataset(:,2))/max(dataset(:,1))*k_scaled;
grid = linspace(10000,80000,70000);

plot(grid, k*grid+m, 'r')
hold on
plot(eng(:,1),eng(:,2),'ro')
plot(fr(:,1),fr(:,2),'bx')

%% Logistic regression

eng = englishData;
fr = frenchData;
dataset = [eng; fr];
data = zeros(size(dataset,1), size(dataset,2));
data(:,1) = dataset(:,1)/max(dataset(:,1));
data(:,2) = dataset(:,2)/max(dataset(:,2));

label = [ones(length(eng),1); zeros(length(fr),1)];

[w_scaled, iterations] = batchGradientAscent(data,label);

m_scaled = -w_scaled(1)/w_scaled(3);
k_scaled = -w_scaled(2)/w_scaled(3);

m = max(dataset(:,2))*m_scaled;
k = max(dataset(:,2))/max(dataset(:,1))*k_scaled;
grid = linspace(10000,80000,70000);

plot(grid, k*grid+m, 'r')
hold on
plot(eng(:,1),eng(:,2),'ro')
plot(fr(:,1),fr(:,2),'bx')
