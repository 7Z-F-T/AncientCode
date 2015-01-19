function [solution, lambda] = NewtonDown2( e_lambda, e )
%Find the solution of x*exp(x)-1=0 using Newton Going Down Hill algorithm

%Start calculation
x = 1;
x_prev = 1;
while(1 == 1)
    lambda = 1;
    while(lambda >= e_lambda)
        x = x_prev - lambda * (x_prev*exp(x_prev)-1) / (exp(x_prev)+x_prev*exp(x_prev));
        if(abs(x*exp(x)-1) < abs(x_prev*exp(x_prev)-1))
            break;
        else
            lambda = lambda/2;
        end
    end
    if(abs(x-x_prev) < e)
        break;
    else
        x_prev = x;
    end
end

solution = x;