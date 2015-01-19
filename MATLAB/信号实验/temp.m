function temp
t=[0:0.001:20]
for n=1:length(t)
    y(n)=exp(-1/t(n));
end
plot(t,y);
end