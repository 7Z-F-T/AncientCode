function [value, error] = Simpson2( n )
%Calculate 4*integral[x=0 to 1 (1/(1+x^2) dx] with multiplex simpson formula(split
%into n pieces)

%Preparation
h = 1 / n;
a = 0;
b = 1;
x = zeros(n+1);
x_ = zeros(n);
for k = 1:(n+1)
    x(k) = a + (k-1)*h;
end
for k = 1:n
    x_(k) = a + (k-0.5)*h;
end

%Start calculation
sum = 0;
sum = sum + 1/(1+a^2);
sum = sum + 1/(1+b^2);

s = 0;
for k = 0:(n-1)
    s = s + 1/(1+x_(k+1)^2);
end
sum = sum + 4*s;

s = 0;
for k = 1:(n-1)
    s = s + 1/(1+x(k+1)^2);
end
sum = sum + 2*s;

value = h/6*sum * 4;
error = value - pi;
    


