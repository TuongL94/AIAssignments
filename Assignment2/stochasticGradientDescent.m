function [w,nbrOfIterations] = stochasticGradientDescent(dataset)
nbrOfIterations = 0;
q = length(dataset(:,1));
epsilon = 0.01;
alpha = 0.7;
w = [1 1]';

update_w0 = @(w, x, y) y-(w(1)+w(2)*x);
update_w1 = @(w, x, y) x*update_w0(w, x, y);
update = @(w, x, y) [update_w0(w, x, y); update_w1(w, x, y)];


grad1 = @(w) -2*sum(update_w0(w, dataset(:,1), dataset(:,2)));
grad2 = @(w) -2*update_w0(w, dataset(:,1), dataset(:,2))'*dataset(:,1);
grad = @(w) [grad1(w); grad2(w)];

%dataset_randomized = [dataset(randperm(q),1) dataset(randperm(q),2)]; 

while norm(grad(w)) > epsilon
    k = randi(q);
    w = w + alpha*update(w, dataset(k,1), dataset(k,2));
    nbrOfIterations = nbrOfIterations + 1;
%     k = k + 1;
%     if k > q
%         dataset_randomized = [dataset(randperm(q),1) dataset(randperm(q),2)];
%         k = 1;
%     end
end
