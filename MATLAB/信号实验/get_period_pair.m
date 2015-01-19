function [period1,period2]=get_period_pair(data)%period1 and period2 are digital period
[f,sample_rate,bit]=wavread(data);
F=fft(f);%compute N point DFT and save it in g(N=L as default)
F=(abs(F));
%find the two omega in the spectrum
%plot(F,'--s');
%find the position of maximum and second maximum in the spectrum
peak_number=1;
for n=2:(length(F)/2)
    if(F(n)>F(n-1) && F(n)>F(n+1))
        peak_pos(peak_number)=n;
        peak_number=peak_number+1;
    end
end

maximum=0;
for n=1:length(peak_pos)
    if(F(peak_pos(n))>maximum)
        max_peak_pos=peak_pos(n);
        maximum=F(peak_pos(n));
    end
end
maximum=0;
for n=1:length(peak_pos)
    if(F(peak_pos(n))>maximum && peak_pos(n)~=max_peak_pos)
        second_max_peak_pos=peak_pos(n);
        maximum=F(peak_pos(n));
    end
end

if(max_peak_pos>second_max_peak_pos)
    temp=max_peak_pos;
    max_peak_pos=second_max_peak_pos;
    second_max_peak_pos=temp;
end
n1=max_peak_pos;
n2=second_max_peak_pos;
omega1=2*n1*pi/length(F);
omega2=2*n2*pi/length(F);
period1=2*pi/omega1;
period2=2*pi/omega2;

%we can also compute analog values
%period1_analog=period1/sample_rate;
%period2_analog=period2/sample_rate;
%freq1_analog=1/period1_analog;
%freq2_analog=1/period2_analog;
