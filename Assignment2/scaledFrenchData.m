function [data] = scaledFrenchData
%FRENCHDATA Summary of this function goes here
%   Detailed explanation goes here
french_dataset = [36961 2503
    43621 2992
    15694 1042
    36231 2487
    29945 2014
    40588 2805
    75255 5062
    37709 2643
    30899 2126
    25486 1784
    37497 2641
    40398 2766
    74105 5047
    76725 5312
    18317 1215];
data = zeros(size(french_dataset,1), size(french_dataset,2));
data(:,1) = french_dataset(:,1)/max(french_dataset(:,1));
data(:,2) = french_dataset(:,2)/max(french_dataset(:,2));
end

