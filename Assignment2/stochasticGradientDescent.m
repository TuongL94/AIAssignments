function [w,nbrOfIterations] = stochasticGradientDescent(dataset)
nbrOfIterations = 0;
q = length(dataset(:,1));
epsilon = 0.00001;
alpha = 1;
w = [1 1]';

update_w0 = @(w, x, y) y-(w(1)+w(2)*x);
update_w1 = @(w, x, y) x*update_w0(w, x, y);
update = @(w, x, y) [update_w0(w, x, y); update_w1(w, x, y)];


grad1 = @(w) -2*sum(update_w0(w, dataset(:,1), dataset(:,2)));
grad2 = @(w) -2*update_w0(w, dataset(:,1), dataset(:,2))'*dataset(:,1);
grad = @(w) [grad1(w); grad2(w)];

% order = randperm(q);
% dataset_rand = [dataset(order,1) dataset(order,2)]; 

w_old = [100 100]';
t = 0;
while norm(w-w_old)/norm(w) > epsilon
    w_old = w;
    k = randi(q);
    w = w + alpha*update(w, dataset(k,1), dataset(k,2));
    nbrOfIterations = nbrOfIterations + 1;
    t = t + 1;
    alpha = 100/(100+t);
end
