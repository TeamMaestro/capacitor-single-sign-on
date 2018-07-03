
  Pod::Spec.new do |s|
    s.name = 'TeammaestroCapacitorSingleSignon'
    s.version = '1.0.0'
    s.summary = 'Single SignOn'
    s.license = 'MIT'
    s.homepage = 'https://github.com/TeamMaestro/capacitor-single-sign-on'
    s.author = 'Osei Fortune'
    s.source = { :git => '', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '10.0'
    s.dependency 'Capacitor'
  end
