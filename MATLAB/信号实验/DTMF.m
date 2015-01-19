function dial_numbers=DTMF(file_name)

[f,sample_rate,bit]=wavread(file_name);
% plot(f);

%separate the wave into many chunks, each of them expresses one
%dial-number,then we analyze them separately.
k=1;
i=1;
while i<length(f)
    if(f(i)>0.6)
        for j=1:300
            sf(k,j)=f(i);%sf means "separated f"
            i=i+1;
        end
        k=k+1;
    end
    i=i+1;
end

%now start to calculate the frequency
dial_pos=1;
for k=1:size(sf,1)

    F=abs(fft(sf(k,:)));

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
    omega1=2*n1*pi/length(F);%digital omega
    omega2=2*n2*pi/length(F);%digital omega
    freq1=omega1*sample_rate/2/pi;%analog freq
    freq2=omega2*sample_rate/2/pi;%analog freq

    %let freq1 be the small one
    if(freq1>freq2)
        temp=freq2;
        freq2=freq1;
        freq1=temp;
    end

    %recognize freq1
    if(freq1<730)
        dial_number_x=1;
    else if(freq1<810)
            dial_number_x=2;
        else if(freq1<890)
                dial_number_x=3;
            else
                dial_number_x=4;
            end
        end
    end

    %recognize freq2
    if(freq2<1270)
        dial_number_y=1;
    else if(freq2<1400)
            dial_number_y=2;
        else if(freq2<1550)
                dial_number_y=3;
            else
                dial_number_y=4;
            end
        end
    end

    x=dial_number_x;
    y=dial_number_y;

    %output the dialed number
    if(x==1 && y==1)
        dial_numbers(dial_pos)=1;
    end
    if(x==1 && y==2)
        dial_numbers(dial_pos)=2;
    end
    if(x==1 && y==3)
        dial_numbers(dial_pos)=3;
    end
    if(x==2 && y==1)
        dial_numbers(dial_pos)=4;
    end
    if(x==2 && y==2)
        dial_numbers(dial_pos)=5;
    end
    if(x==2 && y==3)
        dial_numbers(dial_pos)=6;
    end
    if(x==3 && y==1)
        dial_numbers(dial_pos)=7;
    end
    if(x==3 && y==2)
        dial_numbers(dial_pos)=8;
    end
    if(x==3 && y==3)
        dial_numbers(dial_pos)=9;
    end
    if(x==4 && y==1)
        dial_numbers(dial_pos)=-1;
    end
    if(x==4 && y==2)
        dial_numbers(dial_pos)=0;
    end
    if(x==4 && y==3)
        dial_numbers(dial_pos)=-2;
    end
    dial_pos=dial_pos+1;


end