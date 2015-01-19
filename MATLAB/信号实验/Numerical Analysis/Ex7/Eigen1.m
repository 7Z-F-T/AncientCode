function [ lambda1, x1 ] = Eigen1
%Compute the main eigenvalue and its corresponding eigenvector

%Preparations
v = ones(3,1);
u = ones(3,1);
u_prev = ones(3,1);
A = [5,-4,1;-4,6,-4;1,-4,7];
miu = 0;
miu_prev = 0;

%Start calculation
while(1 == 1)
    v = A*u_prev;
    miu = norm(v,inf);
    u = v/miu;
    if(abs(miu-miu_prev) < 1e-5)
        break;
    else
        u_prev = u;
        miu_prev = miu;
    end
end

lambda1 = miu;
x1 = u;

