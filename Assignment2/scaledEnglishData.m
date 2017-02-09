function [data] = scaledEnglishData
%ENGLISHDATA Summary of this function goes here
%   Detailed explanation goes here
english_dataset = ...
    [35680 2217
    42514	2761
    15162	990
    35298	2274
    29800	1865
    40255	2606
    74532	4805
    37464	2396
    31030	1993
    24843	1627
    36172	2375
    39552	2560
    72545	4597
    75352	4871
    18031	1119];
data = zeros(size(english_dataset,1), size(english_dataset,2));
data(:,1) = english_dataset(:,1)/max(english_dataset(:,1));
data(:,2) = english_dataset(:,2)/max(english_dataset(:,2));
end

