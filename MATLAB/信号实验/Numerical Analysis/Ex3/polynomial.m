function [a,b,c]=polynomial
%define matrix
G = zeros(3,3);
d = zeros(3,1);
phai0 = zeros(15);
phai1 = zeros(15);
phai2 = zeros(15);
co = zeros(3,1);

%initialize
x = [1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8];
y = [33.4, 79.5, 122.65, 159.05, 189.15, 214.15, 238.65, 252.5, 267.55, 280.5, 296.65, 301.65, 310.4, 318.15, 325.15];
for i = 1:15
    phai0(i) = 1;
    phai1(i) = x(i);
    phai2(i) = x(i) * x(i);
end

%compute G
sum = 0;
for i = 1:15
    sum = sum + phai0(i) * phai0(i);
end
G(1,1) = sum;

sum = 0;
for i = 1:15
    sum = sum + phai0(i) * phai1(i);
end
G(1,2) = sum; G(2,1) = sum;

sum = 0;
for i = 1:15
    sum = sum + phai0(i) * phai2(i);
end
G(1,3) = sum; G(3,1) = sum;

sum = 0;
for i = 1:15
    sum = sum + phai1(i) * phai1(i);
end
G(2,2) = sum;

sum = 0;
for i = 1:15
    sum = sum + phai1(i) * phai2(i);
end
G(2,3) = sum; G(3,2) = sum;

sum = 0;
for i = 1:15
    sum = sum + phai2(i) * phai2(i);
end
G(3,3) = sum; G(3,3) = sum;

%compute d
sum = 0;
for i = 1:15
    sum = sum + phai0(i) * y(i);
end
d(1) = sum;

sum = 0;
for i = 1:15
    sum = sum + phai1(i) * y(i);
end
d(2) = sum;

sum = 0;
for i = 1:15
    sum = sum + phai2(i) * y(i);
end
d(3) = sum;
        
%compute coefficient
co = G\d;
a = co(1);
b = co(2);
c = co(3);

%plot a diagraph for demonstration
plot(x,y,'r*');
hold on;
axisX = [1:0.1:8];
axisY = zeros(length(axisX));
for i = 1:length(axisX)
    axisY(i) = a + b*axisX(i) + c*axisX(i)^2;
end
plot(axisX,axisY,'-b');

