total=comment=empty=code=0
while line = gets
  total+=1
  if line=~/(\/\*)(.*)(\*\/)/
    comment+=1
  elsif line=~/\/\*/ ... line=~/\*\//
    comment+=1
  else
    case line
    when /^\s*\/\//
      comment+=1
    when /^$/
      empty+=1
    else
      code+=1
    end
  end
end
puts "total lines:#{total}"
puts "code lines: #{code}"
puts "comment lines: #{comment}"
puts "empty lines: #{empty}"