function value = Lagrange( n , pos )

%Preparations
h = 10/n;
x = zeros(1,n+1);
y = zeros(1,n+1);
for i = 1:n+1
    x(i) = -5 + (i-1)*h;
end
for i = 1:n+1
    y(i) = 1/(1+x(i)^2);
end

%Calculate the Lagrange function
axisX = [-5:0.01:5];
axisY = zeros(1,length(axisX));%axisY is for the interpolated result
axisY2 = zeros(1,length(axisX));%axisY is for the original function
for m = 1:length(axisX)
    sum = 0;
    for k = 1:n+1
       s = 1;
       for i = 1:(k-1)
           s = s * (axisX(m)-x(i))/(x(k)-x(i));
       end
       for i = (k+1):(n+1)
           s = s * (axisX(m)-x(i))/(x(k)-x(i));
       end
       sum = sum + y(k)*s;
    end
    axisY(m) = sum;
    axisY2(m) = 1/(1+axisX(m)^2);
end

%Plot a diagraph to see the result
plot(x,y,'r*');
hold on;
plot(axisX,axisY2,'r');
plot(axisX,axisY,'b');

%Calculate the value of the specified pos
sum = 0;
for k = 1:n+1
   s = 1;
   for i = 1:(k-1)
       s = s * (pos-x(i))/(x(k)-x(i));
   end
   for i = (k+1):(n+1)
       s = s * (pos-x(i))/(x(k)-x(i));
   end
   sum = sum + y(k)*s;
end
value = sum;
