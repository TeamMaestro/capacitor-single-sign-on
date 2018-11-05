
  Pod::Spec.new do |s|
    s.name = 'TeamhiveCapacitorSingleSignon'
    s.version = '2.0.3'
    s.summary = 'Single SignOn'
    s.license = 'MIT'
    s.homepage = 'https://github.com/TeamHive/capacitor-single-sign-on'
    s.author = 'TeamHive'
    s.source = { :git => 'https://github.com/TeamHive/capacitor-single-sign-on', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end
