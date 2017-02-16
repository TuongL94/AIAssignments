function [w, nbrOfIterations] = myPerceptron(label, data)
nbrOfIterations = 0;
dataset = [ones(length(data),1) data];
q = length(label);
nbrOfMisclassified = q;
alpha = 1;
w = [0 0 0]';
while nbrOfMisclassified > 0
    k = randi(q);
    w = w + alpha*(label(k)-(dataset(k,:)*w >= 0))*dataset(k,:)';
    nbrOfMisclassified = sum(((label-(dataset*w >= 0)) ~= 0));
    nbrOfIterations = nbrOfIterations + 1;
    alpha = 1000/(1000+nbrOfIterations);
end
