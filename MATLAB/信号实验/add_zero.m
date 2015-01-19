function f=add_zero(sound_name)
[old_f,original_sample_rate,bit]=wavread(sound_name);
for n=1:length(old_f)
    f(2*n)=old_f(n);
    f(2*n+1)=0;%add zero
end
sample_rate=original_sample_rate*2;
wavwrite(f,sample_rate,'add_zero.wav');