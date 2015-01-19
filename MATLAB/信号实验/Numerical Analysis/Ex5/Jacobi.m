function x = Jacobi
%Find the solution of Ax=b, using Jacobi Algorithm

%Preparations
D = zeros(10,10);
L = zeros(10,10);
U = zeros(10,10);
b = zeros(10,1);
w = 1.25;
for i = 1:10
    for j = 1:10
        if(i == j)
            D(i,j) = 1/(i+j-1);
        elseif(i > j)
            L(i,j) = -1/(i+j-1);
        else
            U(i,j) = -1/(i+j-1);
        end
    end
end
for i = 1:10
    b(i) = 1/i;
end
x = zeros(10,1);
x_prev = zeros(10,1);

%Start calculation
while(1 == 1)
    x = D \ ((L+U)*x_prev + b);
    if(norm((x-x_prev),inf) <= 1e-6)
        break;
    else
        x_prev = x;
    end
end
