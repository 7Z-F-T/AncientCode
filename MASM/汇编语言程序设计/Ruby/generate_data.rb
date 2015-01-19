size = 4000
a = []
for i in 0...size do
  a << (rand * 0xffff).to_i
end

File.open("data.inc", "w") do |f|
  f.puts "dat label word"
  for i in 0...size do
    f.puts "\tdw #{a[i]}"
  end
end

File.open("data.h", "w") do |f|
  f.puts "int dat[] = {"
  for i in 0...size-1 do
    f.puts "\t#{a[i]},"
  end
  f.puts "\t#{a[size-1]}"
  f.puts "};"
end