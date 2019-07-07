clc ; close all ; 
clear all;

CSVD = csvread('12t.csv');
Label = CSVD(1:5400,1:1);


Data = CSVD(1:5400,2:20001);

rng default % for reproducibility
Y = tsne(Data,'Algorithm','barneshut','NumPCAComponents',50);

figure
gscatter(Y(:,1),Y(:,2),Label)


rng default % for fair comparison
Y3 = tsne(Data,'Algorithm','barneshut','NumPCAComponents',50,'NumDimensions',3);
figure
scatter3(Y3(:,1),Y3(:,2),Y3(:,3),15,Label,'filled');
view(-93,14)