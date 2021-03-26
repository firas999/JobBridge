<?php

namespace App\Form;

use App\Entity\CandidatStage;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use App\Entity\Entreprise;
use App\Entity\Stagiaire;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
class CandidatStageType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('DateCandidature')
            ->add('Email')
            ->add('IdEntreprise', EntityType::class, [
                'class'=>Entreprise::class,
                'choice_label'=>'nom',
                
                ])    
           ->add('IDStagiaire',EntityType::class, [
            'class'=>Stagiaire::class,
            'choice_label'=>'nom',])
        ;   
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => CandidatStage::class,
        ]);
    }
}
