function value = SplineValue1( f1, fn )

%Preparation
h = zeros(1,18);
x = zeros(1,19);
y = zeros(1,19);
value = zeros(1,5);
value_pos = zeros(1,5);
x = [0.520,3.1,8.0,17.95,28.65,39.62,50.65,78,104.6,156.6,208.6,260.7,312.5,364.4,416.3,468,494,507,520];
y = [5.288,9.4,13.84,20.2,24.9,28.44,31.10,35,36.9,36.6,34.6,31.0,26.34,20.9,14.8,7.8,3.7,1.5,0.2];
for i = 1:18
    h(i) = x(i+1)-x(i);
end
value_pos = [2,30,130,350,515];
MainDiagnal = zeros(19,19);
for i = 1:19
    MainDiagnal(i,i) = 2;
end

M = zeros(19,1);
lambda = zeros(1,18);
miu = zeros(1,18);
d = zeros(19,1);

%Calculate the coefficients
lambda(1) = 1;
for i = 2:18
    lambda(i) = h(i)/(h(i-1)+h(i));
end

for i = 1:17
    miu(i) = h(i)/(h(i)+h(i+1));
end
miu(18) = 1;

for i = 2:18
    d(i) = 6 * ( (y(i+1)-y(i))/(x(i+1)-x(i)) - (y(i)-y(i-1))/(x(i)-x(i-1)) ) / (h(i-1)+h(i));
end
d(1) = 6/h(1) * ( (y(2)-y(1))/(x(2)-x(1)) -f1 );
d(19) = 6/h(18) * ( fn - (y(18+1)-y(18))/(x(18+1)-x(18)) );

A = MainDiagnal + diag(lambda,1) + diag(miu,-1);
M = A\d;

%Calculate the function value
axisX = [0.520:0.1:520];
axisY = zeros(1,length(axisX));%axisY is for the interpolated result
axisY2 = zeros(1,length(axisX));%axisY is for the original function
for m = 1:length(axisX)
    for i = 1:18
        if(axisX(m)>=x(i) && axisX(m)<=x(i+1))
            j = i;
            break;
        end
    end
    axisY(m) = M(j)*(x(j+1)-axisX(m))^3/(6*h(i)) + M(j+1)*(axisX(m)-x(j))^3/(6*h(i)) + (y(j)-M(j)*h(i)^2/6)*(x(j+1)-axisX(m))/h(i) + (y(j+1)-M(j+1)*h(i)^2/6)*(axisX(m)-x(j))/h(i);
end

%Plot a diagraph to see the result
plot(x,y,'r*');
hold on;
plot(axisX,axisY,'b');

%Calculate the value of the specified pos
for k =1:5
    pos = value_pos(k);
    for i = 1:18
        if(pos>=x(i) && pos<=x(i+1))
            j = i;
            break;
        end
    end
    value(k) = M(j)*(x(j+1)-pos)^3/(6*h(i)) + M(j+1)*(pos-x(j))^3/(6*h(i)) + (y(j)-M(j)*h(i)^2/6)*(x(j+1)-pos)/h(i) + (y(j+1)-M(j+1)*h(i)^2/6)*(pos-x(j))/h(i);
end

