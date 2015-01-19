function [ lambda1, x1 ] = Eigen2
%Compute the main eigenvalue and its corresponding eigenvector

%Preparations
v = ones(4,1);
u = ones(4,1);
u_prev = ones(4,1);
A = [25,-41,10,-6;-41,68,-17,10;10,-17,5,-3;-6,10,-3,2];
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

