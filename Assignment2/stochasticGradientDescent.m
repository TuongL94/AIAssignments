function [w,nbrOfIterations] = stochasticGradientDescent(dataset)
nbrOfIterations = 0;
q = length(dataset(:,1));
epsilon = 0.0001;
alpha = 0.1;
w0 = [1 1]';

update_w0 = @(w, x, y) y-(w(1)+w(2)*x);
update_w1 = @(w, x, y) x*update_w0(w, x, y);
update = @(w, x, y) [update_w0(w, x, y); update_w1(w, x, y)];


% grad1 = @(w, nbr) -2*sum(update_w0(w, dataset(1:nbr+1,1), dataset(1:nbr+1,2)));
% grad2 = @(w, nbr) -2*update_w0(w, dataset(1:nbr+1,1), dataset(1:nbr+1,2))'*dataset(1:nbr+1,1);
% grad = @(w, nbr) [grad1(w, nbr); grad2(w, nbr)];

w = w0;
w_old = w;

while  norm(w-w_old) > epsilon
    w_old = w;
    w = w + alpha*update(w, dataset(nbrOfIterations+1,1), dataset(nbrOfIterations+1,2));
    nbrOfIterations = nbrOfIterations + 1;
end
