require 'mail'

options = {:address => "smtp.gmail.com",
           :port => 587,
           :user_name => 'xxxxxxxxx@gmail.com',
           :password => 'password',
           :authentication => 'plain',
           :enable_starttls_auto => true}


Mail.defaults do
  delivery_method :smtp, options
end


Mail.deliver do
  from "Travis CI"
  to "test.techinsight@gmail.com"
  subject "Test result from Travis-CI, " + Time.now.strftime("%d/%m/%Y %H:%M")

  # body "Hello world"
  html_part do
    content_type 'text/html; charset=UTF-8'
    body '<h1>Funky Title</h1><p>Here is the attachment you wanted</p>'
  end

  # path_att= Dir.pwd + "/pom.xml"
  path_att= Dir.pwd + "/allure_result.tar.gz"
  puts path_att
  add_file path_att
end
