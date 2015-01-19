function [value, error] = Gauss1( n )
%Calculate integral[x=1 to 2 (1/x) dx] with multiplex Gauss formula(split
%into n pieces)

%Preparation
h = 1 / n;
a = 1;
b = 2;
x = zeros(n+1);
x_ = zeros(n);
for k = 1:(n+1)
    x(k) = a + (k-1)*h;
end
for k = 1:n
    x_(k) = a + (k-0.5)*h;
end

%Start calculation
s = 0;
for k = 0:(n-1)
    s = s + 1/(x_(k+1)-h/2/sqrt(3));
    s = s + 1/(x_(k+1)+h/2/sqrt(3));
end

value = h/2*s;
error = value - log(2);
    


