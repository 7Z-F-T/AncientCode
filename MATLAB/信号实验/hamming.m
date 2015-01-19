function hamming(L)
%consider a 0.1 second signal of the following:
%x(t)=cos(2*pi*2000*t)+cos(2*pi*2500*t)+cos(2*pi*3000*t)
%if we use a sample rate of 10kHz to get a sample of the 0.1 second signal
%we would get 10000*0.1=1000 sample points
%
%now let's calculate the value of these points, using a hamming window of length L


for k=0:L-1
    t=k/10000;
    f_rectangle(k+1)=cos(2*pi*2000*t)+cos(2*pi*2500*t)+cos(2*pi*3000*t);
    f_hamming(k+1)=(cos(2*pi*2000*t)+cos(2*pi*2500*t)+cos(2*pi*3000*t))*(0.54-0.46*cos(2*pi*k/(L-1)));
end

% plot(f_rectangle,'b');
% hold on;
% plot(f_hamming,'r');

F_rectangle=fft(f_rectangle);
F_hamming=fft(f_hamming);
plot(abs(F_rectangle),'b');
hold on;
plot(abs(F_hamming),'r');
