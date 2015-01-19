function combine(wavfile1,wavfile2)

[f1,sample_rate,bit]=wavread(wavfile1);
[f2,sample_rate,bit]=wavread(wavfile2);

stop=1990;
offset=2000;
L=length(f1);

%low_pass wavfile1
f1_fft=fft(f1);

w=fix(stop*L/sample_rate);

for n=(w+1):(L-w)
    fs1_fft(n)=0;
end
for n=1:w
    fs1_fft(n)=f1_fft(n);
end
for n=(L-w+1):L
    fs1_fft(n)=f1_fft(n);
end

%low_pass wavfile2
f2_fft=fft(f2);

w=fix(stop*L/sample_rate);

for n=(w+1):(L-w)
    fs2_fft(n)=0;
end
for n=1:w
    fs2_fft(n)=f2_fft(n);
end
for n=(L-w+1):L
    fs2_fft(n)=f2_fft(n);
end

%shift_higher wavfile2
w=fix(offset*L/sample_rate);

for n=1:w
    fss2_fft(n)=0;
end
for n=(1+w):(fix(L/2))
    fss2_fft(n)=fs2_fft(n-w);
end
for n=(fix(L/2)+1):(L-w)
    fss2_fft(n)=fs2_fft(n+w);
end
for n=(L-w+1):L
    fss2_fft(n)=0;
end

% plot(abs(fs1_fft),'b');
% hold on;
% plot(abs(fs2_fft),'g');
% hold on;
% plot(abs(fss2_fft),'r');
% hold on;

%combine
fs3=ifft(fs1_fft)+ifft(fss2_fft);

wavwrite(fs3,8000,'CombinedWav.wav');

