package com.muneemji.app.di;

import com.muneemji.app.parser.TransactionParser;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideTransactionParserFactory implements Factory<TransactionParser> {
  @Override
  public TransactionParser get() {
    return provideTransactionParser();
  }

  public static AppModule_ProvideTransactionParserFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TransactionParser provideTransactionParser() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTransactionParser());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideTransactionParserFactory INSTANCE = new AppModule_ProvideTransactionParserFactory();
  }
}
