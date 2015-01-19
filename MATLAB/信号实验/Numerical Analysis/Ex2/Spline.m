function value = Spline( n, f1, fn, pos )

%Preparation
h = 10/n;
x = zeros(1,n+1);
y = zeros(1,n+1);
for i = 1:n+1
    x(i) = -5 + (i-1)*h;
end
for i = 1:n+1
    y(i) = 1/(1+x(i)^2);
end
MainDiagnal = zeros(n+1,n+1);
for i = 1:n+1
    MainDiagnal(i,i) = 2;
end

M = zeros(n+1,1);
lambda = zeros(1,n);
miu = zeros(1,n);
d = zeros(n+1,1);

%Calculate the coefficients
lambda(1) = 1;
for i = 2:n
    lambda(i) = 0.5;
end

for i = 1:(n-1)
    miu(i) = 0.5;
end
miu(n) = 1;

for i = 2:n
    d(i) = 6 * ( (y(i+1)-y(i))/(x(i+1)-x(i)) - (y(i)-y(i-1))/(x(i)-x(i-1)) ) / (2*h);
end
d(1) = 6/h * ( (y(2)-y(1))/(x(2)-x(1)) -f1 );
d(n+1) = 6/h * ( fn - (y(n+1)-y(n))/(x(n+1)-x(n)) );

A = MainDiagnal + diag(lambda,1) + diag(miu,-1);
M = A\d;

%Calculate the function value
axisX = [-5:0.01:5];
axisY = zeros(1,length(axisX));%axisY is for the interpolated result
axisY2 = zeros(1,length(axisX));%axisY is for the original function
for m = 1:length(axisX)
    for i = 1:n
        if(axisX(m)>=x(i) && axisX(m)<=x(i+1))
            j = i;
            break;
        end
    end
    axisY(m) = M(j)*(x(j+1)-axisX(m))^3/(6*h) + M(j+1)*(axisX(m)-x(j))^3/(6*h) + (y(j)-M(j)*h^2/6)*(x(j+1)-axisX(m))/h + (y(j+1)-M(j+1)*h^2/6)*(axisX(m)-x(j))/h;
    axisY2(m) = 1/(1+axisX(m)^2);
end

%Plot a diagraph to see the result
plot(x,y,'r*');
hold on;
plot(axisX,axisY2,'r');
plot(axisX,axisY,'b');

%Calculate the value of the specified pos
for i = 1:n
    if(pos>=x(i) && pos<=x(i+1))
        j = i;
        break;
    end
end
value = M(j)*(x(j+1)-pos)^3/(6*h) + M(j+1)*(pos-x(j))^3/(6*h) + (y(j)-M(j)*h^2/6)*(x(j+1)-pos)/h + (y(j+1)-M(j+1)*h^2/6)*(pos-x(j))/h;

