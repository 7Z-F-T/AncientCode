app1 = ARGV[0]
#app2 = ARGV[1]

before = Time.new.to_f
`#{app1}`
puts "#{app1} time: #{Time.new.to_f - before}"

#before = Time.new.to_f
#`#{app2}`
#puts "#{app2} time: #{Time.new.to_f - before}"