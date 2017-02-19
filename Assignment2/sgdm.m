function [w,nbrOfIterations] = sgdm(dataset)
nbrOfIterations = 0;
epsilon = 10^-6;
alpha = 0.4;
gamma = 0.9;

update_w0 = @(w, x, y) y-(w(1)+w(2)*x);
update_w1 = @(w, x, y) x*update_w0(w, x, y);
update = @(w, x, y) [update_w0(w, x, y); update_w1(w, x, y)];

wold = [1;1];
w = [2;2];

random_indices = randperm(size(dataset,1))';
shuffled_dataset = [dataset(random_indices,1) dataset(random_indices,2)];

k = 1;
while norm(w-wold)/norm(w) > epsilon
    if k > size(dataset,1)
        k = 1;
        random_indices = randperm(size(dataset,1))';
        shuffled_dataset = [dataset(random_indices,1) dataset(random_indices,2)];
    end
    wtemp = w;
    w = w + alpha*update(w, shuffled_dataset(k,1), shuffled_dataset(k,2)) + gamma*(w-wold);
    wold = wtemp;
    nbrOfIterations = nbrOfIterations + 1;
    k = k+1;
    alpha = alpha*0.995;
end

