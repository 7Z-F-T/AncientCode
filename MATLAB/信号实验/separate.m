function separate(wavfile)

[f,sample_rate,bit]=wavread(wavfile);
f_fft=fft(f);
L=length(f_fft);


offset=2000;

%low_pass wavfile to fs1
stop=1990;
w=fix(stop*L/sample_rate);

for n=(w+1):(L-w)
    fs1_fft(n)=0;
end
for n=1:w
    fs1_fft(n)=f_fft(n);
end
for n=(L-w+1):L
    fs1_fft(n)=f_fft(n);
end

%high_pass wavfile to fs2
stop=2000;
w=fix(stop*L/sample_rate);

for n=(w+1):(L-w)
    fs2_fft(n)=f_fft(n);
end
for n=1:w
    fs2_fft(n)=0;
end
for n=(L-w+1):L
    fs2_fft(n)=0;
end

%shift_lower fs2
w=fix(offset*L/sample_rate);

for n=1:(fix(L/2)-w)
    fss2_fft(n)=fs2_fft(n+w);
end
for n=(fix(L/2)-w+1):fix(L/2)
    fss2_fft(n)=0;
end
for n=(fix(L/2)+1):(fix(L/2)+w)
    fss2_fft(n)=0;
end
for n=(fix(L/2)+w+1):L
    fss2_fft(n)=fs2_fft(n-w);
end

% plot(abs(fs1_fft),'b');
% hold on;
% plot(abs(fs2_fft),'g');
% hold on;
% plot(abs(fss2_fft),'r');
% hold on;

%save into files separately
wavwrite(ifft(fs1_fft),8000,'SeparatedWav1.wav');
wavwrite(ifft(fss2_fft),8000,'SeparatedWav2.wav');
