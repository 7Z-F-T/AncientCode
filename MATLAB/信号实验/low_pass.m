function fs=low_pass(wavfile,stop)  %Shift the inputed wave file's spectrum by offset and return the shifted result fs
[f,sample_rate,bit]=wavread(wavfile);

f_fft=fft(f);
L=length(f_fft);

w=fix(stop*L/sample_rate);

for n=(w+1):(L-w)
    fs_fft(n)=0;
end
for n=1:w
    fs_fft(n)=f_fft(n);
end
for n=(L-w+1):L
    fs_fft(n)=f_fft(n);
end

fs=abs(ifft(fs_fft));

% plot(abs(f_fft),'b');
% hold on;
% plot(abs(fs_fft),'r');

% wavplay(f,sample_rate);
% wavplay(fs,sample_rate);
