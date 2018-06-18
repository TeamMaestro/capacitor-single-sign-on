
  Pod::Spec.new do |s|
    s.name = 'CapacitorCustomTabs'
    s.version = '0.0.3'
    s.summary = 'Custom tabs'
    s.license = 'MIT'
    s.homepage = 'https://github.com/triniwiz/capacitor-custom-tabs'
    s.author = 'Osei Fortune'
    s.source = { :git => '', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}','ios/Plugin/Plugin/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '10.0'
    s.dependency 'Capacitor'
  end
