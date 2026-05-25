package com.muneemji.app.ui;

import com.muneemji.app.repository.OnboardingRepository;
import com.muneemji.app.repository.SheetsRepository;
import com.muneemji.app.repository.SmsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<SmsRepository> repositoryProvider;

  private final Provider<OnboardingRepository> onboardingRepositoryProvider;

  private final Provider<SheetsRepository> sheetsRepositoryProvider;

  public MainViewModel_Factory(Provider<SmsRepository> repositoryProvider,
      Provider<OnboardingRepository> onboardingRepositoryProvider,
      Provider<SheetsRepository> sheetsRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.onboardingRepositoryProvider = onboardingRepositoryProvider;
    this.sheetsRepositoryProvider = sheetsRepositoryProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(repositoryProvider.get(), onboardingRepositoryProvider.get(), sheetsRepositoryProvider.get());
  }

  public static MainViewModel_Factory create(Provider<SmsRepository> repositoryProvider,
      Provider<OnboardingRepository> onboardingRepositoryProvider,
      Provider<SheetsRepository> sheetsRepositoryProvider) {
    return new MainViewModel_Factory(repositoryProvider, onboardingRepositoryProvider, sheetsRepositoryProvider);
  }

  public static MainViewModel newInstance(SmsRepository repository,
      OnboardingRepository onboardingRepository, SheetsRepository sheetsRepository) {
    return new MainViewModel(repository, onboardingRepository, sheetsRepository);
  }
}
