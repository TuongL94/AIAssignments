function [w,nbrOfIterations] = batchGradientDescent(dataset)
nbrOfIterations = 0;
q = length(dataset(:,1));
epsilon = 0.01;
alpha = 0.72;
w0 = [1 1]';

grad_term = @(w, x, y) y-(w(1)+w(2)*x);

grad1 = @(w) -2*sum(grad_term(w, dataset(:,1), dataset(:,2)));

grad2 = @(w) -2*grad_term(w, dataset(:,1), dataset(:,2))'*dataset(:,1);

grad = @(w) [grad1(w); grad2(w)];

w = w0;

while norm(grad(w)) > epsilon
    w = w - alpha/q*grad(w);
    nbrOfIterations = nbrOfIterations + 1;
end


    
