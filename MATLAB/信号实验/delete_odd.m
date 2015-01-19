function f=delete_odd(sound_name)
[old_f,original_sample_rate,bit]=wavread(sound_name);
for n=1:length(old_f)
    if(mod(n,2)==0)
        f(n/2)=old_f(n);%delete odd-number data, only even-number data remains
    end
end
sample_rate=original_sample_rate/2;
wavwrite(f,sample_rate,'delete_odd.wav');
    