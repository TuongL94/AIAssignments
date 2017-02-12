function [w,nbrOfIterations] = batchGradientAscent(dataset,label)
nbrOfIterations = 0;
q = length(dataset(:,1));
epsilon = 0.1;
alpha = 0.75;
w0 = [0 0]';

grad_term = @(w,x,y,z) x.*(y - (1./(1+exp(-w*z)))');
grad1 = @(w) sum(grad_term(w,dataset(:,1),label,dataset'));
grad2 = @(w) sum(grad_term(w,dataset(:,2),label,dataset'));
grad = @(w) [grad1(w); grad2(w)];

w = w0;
while norm(grad(w')) > epsilon
    w = w + alpha/q*grad(w');
    nbrOfIterations = nbrOfIterations + 1;
end


