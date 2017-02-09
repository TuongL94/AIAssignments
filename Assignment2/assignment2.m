dataset1 = scaledEnglishData;
dataset2 = scaledFrenchData;

dataset3 = englishData;
dataset4 = frenchData;

[scaled_w1, nbr1] = batchGradientDescent(dataset1);
[scaled_w2, nbr2] = batchGradientDescent(dataset2);


% grid = linspace(0,1,100);
% 
% figure(1)
% plot(dataset3(:,1),dataset3(:,2),'o')
% hold on
% plot(grid, scaled_w1(1)+scaled_w1(2)*grid, 'r')
% plot(dataset4(:,1),dataset4(:,2),'x')
% plot(grid, scaled_w2(1)+scaled_w2(2)*grid, 'b')


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