%% Loading data
fileID = fopen('encodedData.txt');
C = textscan(fileID,'%f %f %c %f %f %c %f');
fclose(fileID);

label = C{1};
dataset = [C{4} C{7}];
% separate data according to classes
engData = dataset(label == 1,:);
frData = dataset(label == 0,:);
% scale data
dataset_scaled =  bsxfun(@rdivide, dataset, max(dataset));
engData_scaled = bsxfun(@rdivide, engData, max(engData));
frData_scaled = bsxfun(@rdivide, frData, max(frData));

%% Batch gradient descent

[scaled_w_batch_eng, engDataIterations_batch] = batchGradientDescent(engData_scaled);
[scaled_w_batch_fr, frDataIterations_batch] = batchGradientDescent(frData_scaled);

w_batch_eng(1) = max(engData(:,2))*scaled_w_batch_eng(1);
w_batch_eng(2) = max(engData(:,2))/max(engData(:,1))*scaled_w_batch_eng(2);

w_batch_fr(1) = max(frData(:,2))*scaled_w_batch_fr(1);
w_batch_fr(2) = max(frData(:,2))/max(frData(:,1))*scaled_w_batch_fr(2);

grid = linspace(10000,80000,1000);

figure(1)
plot(engData(:,1),engData(:,2),'ro')
hold on
plot(grid, w_batch_eng(1)+w_batch_eng(2)*grid, 'r')
plot(frData(:,1),frData(:,2),'x')
plot(grid, w_batch_fr(1)+w_batch_fr(2)*grid, 'b')
legend('English data', 'Fitted line to english data','French data','Fitted line to english data')

%% Stochastic gradient descent

[scaled_w_stoch_eng, engDataIterations_stoch] = sgdm(engData_scaled);
[scaled_w_stoch_fr, frDataIterations_stoch] = sgdm(frData_scaled);

w_stoch_eng(1) = max(engData(:,2))*scaled_w_stoch_eng(1);
w_stoch_eng(2) = max(engData(:,2))/max(engData(:,1))*scaled_w_stoch_eng(2);

w_stoch_fr(1) = max(frData(:,2))*scaled_w_stoch_fr(1);
w_stoch_fr(2) = max(frData(:,2))/max(frData(:,1))*scaled_w_stoch_fr(2);

grid = linspace(10000,80000,1000);

figure(2)
plot(engData(:,1),engData(:,2),'ro')
hold on
plot(grid, w_stoch_eng(1)+w_stoch_eng(2)*grid, 'r')
plot(frData(:,1),frData(:,2),'x')
plot(grid, w_stoch_fr(1)+w_stoch_fr(2)*grid, 'b')
legend('English data', 'Fitted line to english data','French data','Fitted line to english data')

%% Perceptron
[w_scaled_percep, iterations_percep] = myPerceptron(label, dataset_scaled);

m_scaled_percep = -w_scaled_percep(1)/w_scaled_percep(3);
k_scaled_percep = -w_scaled_percep(2)/w_scaled_percep(3);

m_percep = max(dataset(:,2))*m_scaled_percep;
k_percep = max(dataset(:,2))/max(dataset(:,1))*k_scaled_percep;

grid = linspace(10000,80000,70000);

figure(3)
plot(grid, k_percep*grid+m_percep, 'g')
hold on
plot(engData(:,1),engData(:,2),'ro')
plot(frData(:,1),frData(:,2),'bx')
legend('Decision line', 'English data','French data')

%% Logistic regression

[w_scaled_logistic, iterations_logistic] = batchGradientAscent(label,dataset_scaled);

m_scaled_logistic = -w_scaled_logistic(1)/w_scaled_logistic(3);
k_scaled_logistic = -w_scaled_logistic(2)/w_scaled_logistic(3);

m_logistic = max(dataset(:,2))*m_scaled_logistic;
k_logistic = max(dataset(:,2))/max(dataset(:,1))*k_scaled_logistic;

grid = linspace(10000,80000,70000);

figure(4)
plot(grid, k_logistic*grid+m_logistic, 'g')
hold on
plot(engData(:,1),engData(:,2),'ro')
plot(frData(:,1),frData(:,2),'bx')
legend('Decision line', 'English data','French data')
