function [ pos ] = Divide( M )

for n=3:(length(M)-1)
    Mid(n) = (M(n-2) + M(n-1) + M(n) + M(n+1) + M(n+2)) / 5;
    if(n >= 5)
        if(abs((Mid(n) - Mid(n-1)) / Mid(n)) < 0.0001)
            pos = n;
            break;
        end
    end
end
            
    
