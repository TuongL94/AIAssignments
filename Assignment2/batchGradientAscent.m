function [w,nbrOfIterations] = batchGradientAscent(label, data)
dataset = [ones(length(data),1) data];
nbrOfIterations = 0;
q = length(dataset(:,1));
epsilon = 0.01;
alpha = 20;
w0 = [1 1 1]';

grad_term = @(w,x,y,z) x.*(y - (1./(1+exp(-w*z)))');
grad0 = @(w) sum(grad_term(w,dataset(:,1),label,dataset'));
grad1 = @(w) sum(grad_term(w,dataset(:,2),label,dataset'));
grad2 = @(w) sum(grad_term(w,dataset(:,3),label,dataset'));
grad = @(w) [grad0(w); grad1(w); grad2(w)];

w = w0;
while norm(grad(w')) > epsilon
    w = w + alpha/q*grad(w');
    nbrOfIterations = nbrOfIterations + 1;
end
