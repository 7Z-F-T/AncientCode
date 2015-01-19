function [value,error] = Romberg1(N)
%Calculate integral[x=1 to 2 (1/x) dx] with Romberg Algorithm(using N-level
%iterations

%Preparations
a = 1;
b = 2;
T = zeros(N,N);
T(1,1) = 1/2*(b-a)*(1/a+1/b);


%Start calculation
for i = 1:(N-1)
    n = 2^(i-1);
    h = 1/n;
    x_ = zeros(n);
    for k = 1:n
        x_(k) = a + (k-0.5)*h;
    end
    s = 0;
    for k = 0:(n-1)
        s = s + 1/x_(k+1);
    end
    s = s * h/2;
    T(i+1,1) = 0.5*T(i,1) + s
end

for j = 2:N
    for i = j:N
        T(i,j) = 4^(j-1)/(4^(j-1)-1)*T(i,j-1) - 1/(4^(j-1)-1)*T(i-1,j-1)
    end
end

value = T(N,N);
error = value - log(2);